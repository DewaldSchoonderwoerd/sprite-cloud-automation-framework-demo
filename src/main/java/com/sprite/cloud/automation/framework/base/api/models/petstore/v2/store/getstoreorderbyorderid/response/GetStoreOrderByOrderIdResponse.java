package com.sprite.cloud.automation.framework.base.api.models.petstore.v2.store.getstoreorderbyorderid.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStoreOrderByOrderIdResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("petId")
    @Expose
    private Integer petId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("shipDate")
    @Expose
    private String shipDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("complete")
    @Expose
    private Boolean complete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "GetStoreOrderByOrderIdResponse{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status='" + status + '\'' +
                ", complete=" + complete +
                '}';
    }
}