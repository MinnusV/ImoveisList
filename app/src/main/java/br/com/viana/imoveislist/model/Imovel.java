package br.com.viana.imoveislist.model;

/**
 * Created by Vinicius Viana on 28/04/2017.
 */

public class Imovel {

    private String name;
    private String price;
    private String address;
    private Double note;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getNote() {

        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.getName();
    }
}
