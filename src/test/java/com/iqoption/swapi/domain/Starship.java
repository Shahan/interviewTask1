package com.iqoption.swapi.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Starship {
    @SerializedName("name")
    private String name;

    @SerializedName("model")
    private String model;

    @SerializedName("manufacturer")
    private String manufacturer;

    @SerializedName("cost_in_credits")
    private String costInCredits;

    @SerializedName("length")
    private String length;

    @SerializedName("max_atmosphering_speed")
    private String maxAtmospheringSpeed;

    @SerializedName("crew")
    private String crew;

    @SerializedName("passengers")
    private String passengers;

    @SerializedName("cargo_capacity")
    private String cargoCapacity;

    @SerializedName("consumables")
    private String consumables;

    @SerializedName("hyperdrive_rating")
    private String hyperdriveRating;

    @SerializedName("MGLT")
    private String mglt;

    @SerializedName("starship_class")
    private String starshipClass;

    @SerializedName("created")
    private String created;

    @SerializedName("edited")
    private String edited;

    @SerializedName("url")
    private String url;
}
