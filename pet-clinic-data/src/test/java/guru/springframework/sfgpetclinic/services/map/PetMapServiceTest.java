package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {

    PetMapService petMapService;
    Long id = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<Pet> allPets = petMapService.findAll();

        assertEquals(1, allPets.size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(id);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(id));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void saveNotSetId() {
        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void saveSetId() {
        Long idPet = 2L;
        Pet savedPet = petMapService.save(Pet.builder().id(idPet).build());

        assertEquals(idPet, savedPet.getId());
    }

    @Test
    void findById() {
        Pet petById = petMapService.findById(id);
        assertEquals(id, petById.getId());
    }
}