package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository repository;

    private static final String EACH = "each";

    @Before
    public void setUp() {
        repository.deleteAll().block();
    }

    @Test
    public void testRepoSave() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(EACH);

        repository.save(uom).block();

        Long count = repository.count().block();

        Assert.assertEquals(Long.valueOf(1), count);
    }

    @Test
    public void testFindByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(EACH);

        repository.save(uom).block();

        UnitOfMeasure foundUnitOfMeasure = repository.findByDescription(EACH).block();

        Assert.assertNotNull(foundUnitOfMeasure);
        Assert.assertEquals(EACH, foundUnitOfMeasure.getDescription());

    }


}
