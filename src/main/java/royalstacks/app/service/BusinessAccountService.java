package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.SectorAndTotalBalance;
import royalstacks.app.model.repository.BusinessAccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessAccountService {

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    public List<SectorAndTotalBalance> findSectorAndTotalBalance(){
        List<Object[]> results = businessAccountRepository.findSectorAndTotalBalance();
        List<SectorAndTotalBalance> sectorAndTotalBalances = new ArrayList<>();

        for (Object[] result : results) {
            sectorAndTotalBalances.add(
                    new SectorAndTotalBalance(
                            (String) result[0],
                            (double) result[1]
                    )
            );
        }
        return sectorAndTotalBalances;

    }
}
