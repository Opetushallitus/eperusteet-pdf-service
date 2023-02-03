package fi.vm.sade.eperusteet.pdf.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.PerusteenOsaUpdateDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.UpdateDto;

import java.io.IOException;

public class PerusteenOsaUpdateDtoDeserializer extends StdDeserializer<PerusteenOsaUpdateDto> {

    public PerusteenOsaUpdateDtoDeserializer() {
        super(PerusteenOsaUpdateDto.class);
    }

    @Override
    public PerusteenOsaUpdateDto deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final TreeNode tree = jp.readValueAsTree();
        final ObjectCodec codec = jp.getCodec();
        PerusteenOsaUpdateDto dto = new PerusteenOsaUpdateDto();
        if (tree.get("metadata") != null) {
            dto.setMetadata(codec.treeToValue(tree.get("metadata"), UpdateDto.MetaData.class));
        }
        dto.setDto(codec.treeToValue(((ObjectNode)tree).without("metadata"), PerusteenOsaDto.Laaja.class));
        return dto;
    }
}
