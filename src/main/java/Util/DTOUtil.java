package Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DTOUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T, U> T convert(Object o, TypeReference<U> ref) {
        return mapper.convertValue(o, ref);
    }

}
