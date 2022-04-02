import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.models.Owner;
import ru.itmo.service.CatService;
import ru.itmo.service.OwnerService;

import java.time.LocalDate;

public class KotikiTest {
    private CatService catService;
    private OwnerService ownerService;

    @Before
    public void setUp() {
        catService = new CatService();
        ownerService = new OwnerService();
    }

    @Test
    public void createOwner() {
        int size = ownerService.findAll().size();
        ownerService.create("Fedor", LocalDate.of(2001, 3, 31));
        Assert.assertEquals(size + 1, ownerService.findAll().size());
    }

    @Test
    public void createOwner_addCat() {
        Owner owner = ownerService.create("Kristina", LocalDate.of(2007, 12, 7));
        Cat cat = catService.create("Stefan", LocalDate.of(2022,4,1), Color.CALICO,
                "Balinese", owner);
        Assert.assertEquals(catService.findById(cat.getId()).getId(), cat.getId());
    }

    @Test
    public void deleteCat() {
        Owner owner = ownerService.create("Lev", LocalDate.of(20011, 8, 21));
        Cat cat = catService.create("Bella", LocalDate.of(2018,9,3), Color.BLACK,
                "Siamese", owner);
        int size = ownerService.findAll().size();
        catService.delete(cat);
        Assert.assertNotEquals(size, catService.findAll().size());;
    }
}
