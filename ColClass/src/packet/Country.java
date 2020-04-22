package packet;

import java.io.Serializable;

/**
 * Enum, хранящий возможные варианты национальностей для packet.Person
 * @author Maxim Antonov and Andrey Lyubkin
 */
public enum Country implements Serializable {
    GERMANY,
    CHINA,
    VATICAN,
    SOUTH_KOREA,
    NORTH_KOREA;
}