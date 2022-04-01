import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.itmo.dao.AbstractDao;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.service.CatService;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class KotikiTestMockito {

    @Mock
    private AbstractDao catDao;
    @InjectMocks
    private CatService catService;

    @Test
    public void createCat() {
        Cat mockCat = new Cat("Stefan", LocalDate.of(2022, 04, 01), Color.CALICO, "Balinese ");
        when(catDao.findById(1)).thenReturn(mockCat);

        Cat cat = catService.findById(1);
        verify(catDao).findById(1);

        Assert.assertEquals(mockCat, cat);

    }

}
