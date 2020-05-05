package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VisitMapServiceTest {

    VisitMapService visitMapService;
    Long id = 1L;
    Owner owner;
    Pet pet;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(8L).build();
        pet = Pet.builder().id(9L).owner(owner).build();

        visitMapService = new VisitMapService();
        visitMapService.save(Visit.builder().id(id).pet(pet).build());
    }

    @Test
    void findAll() {
        Set<Visit> allVisits = visitMapService.findAll();
        assertEquals(1, allVisits.size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(id);
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(id));
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void saveNotSetId() {
        Visit savedVisit = visitMapService.save(Visit.builder().pet(pet).build());
        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
    }

    @Test
    void saveSetId() {
        Long visitId = 2L;
        Visit savedVisit = visitMapService.save(Visit.builder().id(visitId).pet(pet).build());
        assertEquals(visitId, savedVisit.getId());
    }

    @Test
    void findById() {
        Visit visitById = visitMapService.findById(id);
        assertEquals(id, visitById.getId());
    }
}