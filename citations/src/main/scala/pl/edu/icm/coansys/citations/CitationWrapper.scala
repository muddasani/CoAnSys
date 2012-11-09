package pl.edu.icm.coansys.citations

import pl.edu.icm.coansys.importers.models.DocumentProtos.DocumentMetadata
import com.nicta.scoobi.core.Grouping

/**
 * @author Mateusz Fedoryszak (m.fedoryszak@icm.edu.pl)
 */
class CitationWrapper(val meta: DocumentMetadata) {
  def normalisedAuthorTokens: Iterable[String] = {
    if (meta.getAuthorCount > 0)
      util.normalizedAuthorTokensFromAuthorList(meta)
    else
      meta.getText.split( """[^\p{L}]+""").filter(_.length > 1).toSet
  }
}

object CitationWrapper {
  implicit val converter =
    new BytesConverter[CitationWrapper](
      (_.meta.toByteArray),
      (b => new CitationWrapper(DocumentMetadata.parseFrom(b))))

  implicit val grouping = new Grouping[CitationWrapper] {
    def groupCompare(x: CitationWrapper, y: CitationWrapper) = x.meta.getKey.compare(y.meta.getKey)
  }
}
