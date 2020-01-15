package by.ipps.dao.utils.seriazable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.springframework.data.domain.Page;

public class PageSerializer extends StdSerializer<Page> {

  public PageSerializer() {
    super(Page.class);
  }

  @Override
  public void serialize(Page value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("number", value.getNumber());
    gen.writeNumberField("numberOfElements", value.getNumberOfElements());
    gen.writeNumberField("totalElements", value.getTotalElements());
    gen.writeNumberField("totalPages", value.getTotalPages());
    gen.writeNumberField("size", value.getSize());
    gen.writeFieldName("content");
    provider.defaultSerializeValue(value.getContent(), gen);
    gen.writeEndObject();
  }

}