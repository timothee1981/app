package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Sector;
import royalstacks.app.model.SectorAndAverageBalance;
import royalstacks.app.model.repository.BusinessAccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessAccountService {

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    public List<SectorAndAverageBalance> findSectorAndAverageBalance(){
        List<Object[]> results = businessAccountRepository.findSectorAndAverageBalance();
        List<SectorAndAverageBalance> sectorAndAverageBalances = new ArrayList<>();

        for (Object[] result : results) {
            sectorAndAverageBalances.add(
                    new SectorAndAverageBalance(
                            Sector.valueOf((String) result[0]),
                            (double) result[1]
                    )
            );
        }
        return sectorAndAverageBalances;

    }
}
