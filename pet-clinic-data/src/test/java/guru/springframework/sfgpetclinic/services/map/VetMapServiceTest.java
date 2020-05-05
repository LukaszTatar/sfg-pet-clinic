package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VetMapServiceTest {

    VetMapService vetMapService;
    Long id = 1L;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialityMapService());
        vetMapService.save(Vet.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<Vet> allVets = vetMapService.findAll();
        assertEquals(1, allVets.size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(id);
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(id));
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void saveNotSetId() {
        Vet savedVet = vetMapService.save(Vet.builder().build());
        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
    }

    @Test
    void saveSetId() {
        Long vetId = 2L;
        Vet savedVet = vetMapService.save(Vet.builder().id(vetId).build());
        assertEquals(vetId, savedVet.getId());
    }

    @Test
    void findById() {
        Vet vetById = vetMapService.findById(id);
        assertEquals(id, vetById.getId());
    }
}