import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itmo.KotikiApplication;
import ru.itmo.models.Cat;
import ru.itmo.models.Friendship;
import ru.itmo.models.Owner;
import ru.itmo.repository.ICatRepository;
import ru.itmo.repository.IFriendshipRepository;
import ru.itmo.repository.IOwnerRepository;
import ru.itmo.service.CatService;
import ru.itmo.service.FriendshipService;
import ru.itmo.service.OwnerService;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KotikiApplication.class)
public class KotikiSpringTest {

    @Autowired
    private OwnerService ownerService;

    @MockBean
    private IOwnerRepository ownerRepository;

    @Autowired
    private CatService catService;

    @MockBean
    private ICatRepository catRepository;

    @Autowired
    private FriendshipService friendshipService;

    @MockBean
    private IFriendshipRepository friendshipRepository;

    @Test
    public void testOwnerService() {
        Owner owner = new Owner("Klara", LocalDate.of(1999,9,1));
        ownerService.saveOwner(owner);
        Mockito.verify(ownerRepository, Mockito.times(1)).save(owner);
        Assert.assertEquals("Klara", owner.getName());
        Mockito.doReturn(owner).when(ownerRepository).findById(0);
        Assert.assertEquals(owner, ownerService.getOwnerById(0));
    }

    @Test
    public void testCatService() {
        Owner owner = new Owner("Zina", LocalDate.of(2007,3,28));
        Cat cat = new Cat("Sandra", LocalDate.of(2022, 4, 15), "Bengali",
                3, owner);
        catService.saveCat(cat);
        Mockito.verify(catRepository, Mockito.times(1)).save(cat);
        Assert.assertEquals(3, cat.getColorId());
        Mockito.doReturn(cat).when(catRepository).findById(0);
        Assert.assertEquals(cat, catService.getCatById(0));
    }

    @Test
    public void testFriendshipService() {
        Owner owner = new Owner("Pavel", LocalDate.of(2000,1,1));
        Cat firstCat = new Cat("Leo", LocalDate.of(2019, 6, 5), "Burmese",
                1, owner);
        Cat secondCat = new Cat("Kate", LocalDate.of(2019, 6, 6), "Burmese",
                1, owner);
        Friendship friendship = new Friendship(firstCat.getId(), secondCat.getId());
        friendshipService.saveFriendship(friendship);
        Mockito.verify(friendshipRepository, Mockito.times(1)).save(friendship);
    }
}
