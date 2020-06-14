package royalstacks.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import royalstacks.app.model.Sector;
import royalstacks.app.model.SectorAndAverageBalance;
import royalstacks.app.model.repository.BusinessAccountRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BusinessAccountServiceTest {

    @Mock
    BusinessAccountRepository businessAccountRepository;

    @InjectMocks
    BusinessAccountService businessAccountService;

    @Test
    public void findSectorAndAverageBalanceTest() {
        //ARRANGE
        List<Object[]> mockedList = new ArrayList<>();
        mockedList.add(new Object[]{"CULTURE_SPORTS_AND_RECREATION",666.66});
        mockedList.add(new Object[]{"WHOLESALE", 1337.00});
        when(businessAccountRepository.findSectorAndAverageBalance()).thenReturn(mockedList);

        //ACT
        List<SectorAndAverageBalance> actual = businessAccountService.findSectorAndAverageBalance();

        //ASSERT
        //Size SectorAndAverageBalance-list
        assertEquals(2,actual.size());

        SectorAndAverageBalance firstResult = actual.get(0);
        //Check sector in first record from SectorAndAverageBalance-list
        assertEquals(Sector.CULTURE_SPORTS_AND_RECREATION,firstResult.getSector());
        //Check balance in first record from SectorAndAverageBalance-list
        assertEquals(666.66,firstResult.getTotalBalance(),0);

        SectorAndAverageBalance secondResult = actual.get(1);
        //Check sector in first record from SectorAndAverageBalance-list
        assertEquals(Sector.WHOLESALE,secondResult.getSector());
        //Check balance in first record from SectorAndAverageBalance-list
        assertEquals(1337.00,secondResult.getTotalBalance(),0);
    }
}