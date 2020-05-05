package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpecialityMapServiceTest {

    SpecialityMapService specialityMapService;
    Long id = 1L;

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        specialityMapService.save(Speciality.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<Speciality> allSpecialties = specialityMapService.findAll();
        assertEquals(1, allSpecialties.size());
    }

    @Test
    void deleteById() {
        specialityMapService.deleteById(id);
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void delete() {
        specialityMapService.delete(specialityMapService.findById(id));
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void saveSetId() {
        Long specialtyId = 2L;
        Speciality savedSpecialty = specialityMapService.save(Speciality.builder().id(specialtyId).build());
        assertEquals(specialtyId, savedSpecialty.getId());
    }

    @Test
    void saveNotSetId() {
        Speciality savedSpecialty = specialityMapService.save(Speciality.builder().build());
        assertNotNull(savedSpecialty);
        assertNotNull(savedSpecialty.getId());
    }

    @Test
    void findById() {
        Speciality specialityById = specialityMapService.findById(id);
        assertEquals(id, specialityById.getId());
    }
}