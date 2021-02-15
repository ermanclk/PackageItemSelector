package com;

import com.domain.Item;
import com.domain.PackageConfig;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.TestUtil.getOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class PackageItemSelectorTest {

    Item dummyItem1=new Item("1", 10.0, 30);
    Item dummyItem2=new Item("2", 20.0, 20);
    Item dummyItem3=new Item("3", 30.0, 10);
    Item dummyItem4=new Item("4", 20.0, 10);
    Item dummyItem5=new Item("5", 50.0, 100);

    private static final int DUMMY_CAPACITY = 30;

    @Test
    void givenItemsWhenfillPackageThenReturnSetWithMaxValue() {
        //Arrange
        PackageConfig config = new PackageConfig(DUMMY_CAPACITY,
                getOptions(dummyItem1,dummyItem2,dummyItem3));

        //Act
        Set<Item> items = PackageItemSelector.fillPackage(config);

        //Assert
        Integer totalPrice = items.stream().mapToInt(Item::getPrice).sum();
        assertThat(totalPrice,is(equalTo(50)));
        assertThat(items.contains(dummyItem1),is(equalTo(true)));

    }



    @Test
    void givenSamePriceItemsWhenfillPackageThenSelectLowerWeight() {
        //Arrange
        PackageConfig config = new PackageConfig(DUMMY_CAPACITY,
                getOptions(dummyItem3,dummyItem4));
        //Act
        Set<Item> items = PackageItemSelector.fillPackage(config);

        //Assert
        Integer totalPrice = items.stream().mapToInt(Item::getPrice).sum();
        Double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        assertThat(totalPrice,is(equalTo(dummyItem4.getPrice())));
        assertThat(totalWeight,is(equalTo(dummyItem4.getWeight())));

    }

    @Test
    void givenSingleItemsWhenfillPackageThenReturnItem() {
        //Arrange
        Set<Item> options = getOptions(dummyItem1);
        PackageConfig config = new PackageConfig(DUMMY_CAPACITY,
                options);

        //Act
        Set<Item> items = PackageItemSelector.fillPackage(config);

        //Assert
        Integer totalPrice = items.stream().mapToInt(Item::getPrice).sum();
        assertThat(totalPrice, is(equalTo(dummyItem1.getPrice())));
        assertThat(items.size(), is(equalTo(options.size())));
        assertThat(items.contains(dummyItem1),is(equalTo(true)));

    }

    /**
     * If all items have same values, then returns only one of correct
     */
    @Test
    void givenSameWeightItemsWhenfillPackageThenReturnHigherPrice() {
        //Arrange
        PackageConfig config = new PackageConfig(DUMMY_CAPACITY,
                getOptions(dummyItem2,dummyItem4));
        //Act
        Set<Item> items = PackageItemSelector.fillPackage(config);

        //Assert
        Integer totalPrice = items.stream().mapToInt(Item::getPrice).sum();
        Double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        assertThat(totalPrice,is(equalTo(dummyItem2.getPrice())));
        assertThat(totalWeight,is( equalTo(dummyItem2.getWeight())));

    }


    /**
     * If all items have same values, then returns only one of correct
     */
    @Test
    void givenItemsAboveCapacityWhenfillPackageThenReturnEmpty() {
        //Arrange
        PackageConfig config = new PackageConfig(DUMMY_CAPACITY,
                getOptions(dummyItem5));
        //Act
        Set<Item> items = PackageItemSelector.fillPackage(config);

        //Assert
        Integer totalPrice = items.stream().mapToInt(Item::getPrice).sum();
        Double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();

        assertThat(totalPrice,is(equalTo(0)));
        assertThat(totalWeight,is( equalTo(0.0)));

    }

    /**
     * If all items have same values, then returns only one of correct
     */
    @Test
    void givenTotalItemsUnderCapacityWhenfillPackageThenReturnAll() {
        //Arrange
        Set<Item> options = getOptions(dummyItem1, dummyItem2, dummyItem3,dummyItem4);
        Integer totalPrice = options.stream().mapToInt(Item::getPrice).sum();
        double totalWeight = options.stream().mapToDouble(Item::getWeight).sum();

        PackageConfig config = new PackageConfig((int) totalWeight, options);

        //Act
        Set<Item> items = PackageItemSelector.fillPackage(config);

        //Assert
        Integer actualPrice = items.stream().mapToInt(Item::getPrice).sum();
        Double actualWeight = items.stream().mapToDouble(Item::getWeight).sum();

        assertThat(actualWeight,is(equalTo(totalWeight)));
        assertThat(actualPrice,is(equalTo(totalPrice)));
        assertThat(items,is( equalTo(options)));

    }
}