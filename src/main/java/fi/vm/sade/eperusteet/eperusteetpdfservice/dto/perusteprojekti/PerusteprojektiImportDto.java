package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.liite.LiiteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.TermiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerusteprojektiImportDto {
    private PerusteprojektiLuontiDto projekti;
    private PerusteKaikkiDto peruste;
    List<TermiDto> termit;
    HashMap<UUID, byte[]> liitetiedostot;
    List<LiiteDto> liitteet;
}
