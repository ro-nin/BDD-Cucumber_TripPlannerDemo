package EmCio;

import EmCio.Model.ParkingSpot;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return ENGLISH;
    }
    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
                "parkingSpot",
                ".*",
                ParkingSpot.class,
                (String s) -> new ParkingSpot((s)))
        );
        typeRegistry.defineDataTableType(new DataTableType(
                ParkingSpot.class,
                (String s) -> new ParkingSpot(s))
        );
    }

}
