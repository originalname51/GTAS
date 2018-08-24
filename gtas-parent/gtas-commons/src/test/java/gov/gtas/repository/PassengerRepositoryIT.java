package gov.gtas.repository;

import gov.gtas.config.CachingConfig;
import gov.gtas.config.CommonServicesConfig;
import gov.gtas.services.dto.PassengersRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommonServicesConfig.class,CachingConfig.class })
@TransactionConfiguration(transactionManager = "transactionManager")
public class PassengerRepositoryIT {

    @Autowired
    private PassengerRepository passengerDao;

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory
            .getLogger(PassengerRepositoryIT.class);

    //@Test
    //@Transactional
    public void testRetrieveNotNullIdTagPax() {

    }

    @Test
    public void passengerRepositoryImplClass() {

        PassengerRepositoryImplTestClass passengerRepositoryImplTestClass = new PassengerRepositoryImplTestClass(em);
        passengerRepositoryImplTestClass.findByCriteria(1L, new PassengersRequestDto());
    }

}
