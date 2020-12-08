package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository repository;

    private String mexicanString = "Mexican";

    @Before
    public void setup() {
        repository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription(mexicanString);

        repository.save(category).block();

        Long count = repository.count().block();
        Assert.assertEquals(Long.valueOf(1), count);
    }

    @Test
    public void testFindByDescription(){
        Category category = new Category();
        category.setDescription(mexicanString);

        repository.save(category).block();
        Category foundCategory = repository.findByDescription(mexicanString).block();

        Assert.assertNotNull(foundCategory);
    }
}
