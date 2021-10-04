package fi.mobts.hyvinvointilaskuri.classes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ObservationDeserializer implements JsonDeserializer<Observation> {

    @Override
    public Observation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String type = json.getAsJsonObject().get("type").getAsString();
        switch(type) {
            case "height":
                return context.deserialize(json, HeightObservation.class);
            case "weight":
                return context.deserialize(json, WeightObservation.class);
            default:
                throw new IllegalArgumentException("Neither height or weight");
        }
    }
}
