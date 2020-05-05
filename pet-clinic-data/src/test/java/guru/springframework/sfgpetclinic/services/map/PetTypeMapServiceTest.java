package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        Set<PetType> allPetTypes = petTypeMapService.findAll();

        assertEquals(1, allPetTypes.size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(petTypeId);

        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void saveSetId() {
        Long id = 2L;
        PetType savedPetType = petTypeMapService.save(PetType.builder().id(id).build());

        assertEquals(id, savedPetType.getId());
    }

    @Test
    void saveNotSetId() {
        PetType savedPetType = petTypeMapService.save(PetType.builder().build());

        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
    }

    @Test
    void findById() {
        PetType petTypeById = petTypeMapService.findById(petTypeId);
        assertEquals(petTypeId, petTypeById.getId());
    }
}