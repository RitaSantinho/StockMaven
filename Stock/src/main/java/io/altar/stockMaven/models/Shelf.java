package io.altar.stockMaven.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Shelf.GET_ALL_SHELVES, query = "SELECT s FROM Shelf s"),
		@NamedQuery(name = Shelf.GET_SHELVES_COUNT, query = "SELECT COUNT(s.id) FROM Shelf s"),
		@NamedQuery(name = Shelf.FIND_EMPTY_SHELVES, query = "SELECT s FROM Shelf s WHERE s.product = null"),
		@NamedQuery(name = Shelf.FIND_BY_PRODUCT_ID, query = "SELECT s FROM Shelf s WHERE s.product.id = :id"),
		@NamedQuery(name = Shelf.SET_SHELF_PRODUCT_TO_NULL, query = "UPDATE Shelf s SET s.product = null WHERE s.product.id= :productId"),
// @NamedQuery(name=Shelf.UPDATE_PRODUCT,
// query="UPDATE Shelf s SET s.product = null WHERE s.product.id= :productId")
})
public class Shelf extends Entity2_ {

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_SHELVES = "getAllShelves";
	public static final String GET_SHELVES_COUNT = "howManyShelves";
	public static final String FIND_EMPTY_SHELVES = "getEmptyShelves";
	public static final String FIND_BY_PRODUCT_ID = "getShelvesByProductId";
	public static final String SET_SHELF_PRODUCT_TO_NULL = "remProductFromShelves";
	private int capacity;
	@ManyToOne
	private Product product;
	private float price;

	public Shelf(int capacity, float price) {
		super();
		this.capacity = capacity;
		this.price = price;
	}

	public Shelf() {
	}

	@Override
	public String toString() {
		return "Shelf [id=" + this.getId() + ", capacity=" + capacity + ", product=" + product + ", price=" + price
				+ "]";
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	};

}
