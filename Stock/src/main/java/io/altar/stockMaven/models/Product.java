package io.altar.stockMaven.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Product.GET_ALL_PRODUCTS, query = "SELECT p FROM Product p"),
		@NamedQuery(name = Product.HOW_MANY_PRODUCTS, query = "SELECT COUNT (p.id) FROM Product p") })
public class Product extends Entity2_ {

	private static final long serialVersionUID = 1L;
	public static final String HOW_MANY_PRODUCTS = "howManyProducts";
	public static final String GET_ALL_PRODUCTS = "getAllProducts";

	private int iva;
	private float pvp;
	private float discount;

	public Product() {
	}

	@Override
	public String toString() {
		return "Product [id=" + this.getId() + ", discont=" + discount + ", iva=" + iva + ", pvp=" + pvp + "]";
	}

	public int getIva() {

		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	};

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

}
