package com.example.imageboard.entity.test;

import com.example.imageboard.entity.*;
import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntityControllerTest {

    @InjectMocks
    private EntityController<EntityModel, Long, EntityDto, EntityService<EntityModel, Long, EntityDto>> entityController;

    @Mock
    private EntityService<EntityModel, Long, EntityDto> entityService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private WebRequest webRequest;

    @Test
    void handleEntityNotFound() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");
        ResponseEntity<ErrorResponseDto> response = entityController.handleEntityNotFound(ex, webRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Entity not found");
    }

    @Test
    void handleInvalidEntity() {
        InvalidEntityException ex = new InvalidEntityException(List.of("Validation error 1", "Validation error 2"));
        ResponseEntity<ErrorResponseDto> response = entityController.handleInvalidEntity(ex, webRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Validation failed");
        assertThat(response.getBody().getValidationErrors()).containsExactly("Validation error 1", "Validation error 2");
    }

    @Test
    void getAllEntities() {
        List<EntityDto> dtos = List.of(new EntityDto(), new EntityDto());
        when(entityService.findAll()).thenReturn(dtos);

        ResponseEntity<List<EntityDto>> response = entityController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dtos);
        verify(entityService).findAll();
    }

    @Test
    void getEntityById() {
        Long id = 1L;
        EntityDto dto = new EntityDto();
        when(entityService.findById(id)).thenReturn(dto);

        ResponseEntity<EntityDto> response = entityController.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
        verify(entityService).findById(id);
    }

    @Test
    void createEntity() {
        EntityDto dto = new EntityDto();
        dto.setId(1L);
        EntityModel model = new EntityModel();
        URI location = URI.create("/entities/1");

        when(modelMapper.map(dto, EntityModel.class)).thenReturn(model);
        when(entityService.save(model)).thenReturn(dto);

        // Mock ServletUriComponentsBuilder
        when(ServletUriComponentsBuilder.fromCurrentRequestUri()).thenReturn(mock(ServletUriComponentsBuilder.class));
        when(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri()).thenReturn(location);

        ResponseEntity<EntityDto> response = entityController.create(dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isEqualTo(location);
        verify(entityService).save(model);
    }

    @Test
    void updateEntity() {
        Long id = 1L;
        EntityDto dto = new EntityDto();
        EntityModel model = new EntityModel();
        when(modelMapper.map(dto, EntityModel.class)).thenReturn(model);
        when(entityService.save(model)).thenReturn(dto);
        when(entityService.findOrThrow(id)).thenReturn(model);

        ResponseEntity<EntityDto> response = entityController.update(id, dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
        verify(entityService).save(model);
        verify(entityService).findOrThrow(id);
    }

    @Test
    void deleteEntity() {
        Long id = 1L;
        doNothing().when(entityService).delete(id);

        ResponseEntity<Void> response = entityController.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(entityService).delete(id);
    }
}
