package com.example.imageboard.entity.test;

import com.example.imageboard.entity.EntityController;
import com.example.imageboard.entity.EntityDto;
import com.example.imageboard.entity.EntityModel;
import com.example.imageboard.entity.EntityService;
import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class EntityControllerTest {

        @InjectMocks // The class to test
        private EntityController<EntityModel, Long, EntityDto, EntityService<EntityModel, Long, EntityDto>> entityController;

        @Mock // Mocked dependencies
        private EntityService<EntityModel, Long, EntityDto> entityService;

        @Mock
        private WebRequest webRequest;

        // ... other necessary mocks (ModelMapper, Validator, etc.)

        @Test
        void handleEntityNotFound() {
            EntityNotFoundException ex = new EntityNotFoundException("Entity not found");

            ResponseEntity<ErrorResponseDto> response = entityController.handleEntityNotFound(ex, webRequest);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(response.getBody().getMessage()).isEqualTo("Entity not found");
            // ... assert other error response details
        }

        @Test
        void handleInvalidEntity() {
            InvalidEntityException ex = new InvalidEntityException(List.of("Validation error 1", "Validation error 2"));

            ResponseEntity<ErrorResponseDto> response = entityController.handleInvalidEntity(ex, webRequest);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody().getMessage()).isEqualTo("Validation failed");
            assertThat(response.getBody().getValidationErrors()).containsExactly("Validation error 1", "Validation error 2");
            // ... assert other error response details
        }

        // Tests for CRUD methods (getAll, getById, create, update, delete)
        // ... use Mockito to mock the behavior of entityService
        // ... verify that the correct methods are called on the service
        // ... and that the correct responses are returned

    }

}
