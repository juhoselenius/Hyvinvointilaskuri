package fi.mobts.hyvinvointilaskuri.classes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * The class <code>ObservationDeserializer</code> is used to help define the arbitrary type object <code>Observation</code> for JSON conversion.
 * @author Juho Selenius
 * @version 1.0 (13.10.2021)
 */

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
