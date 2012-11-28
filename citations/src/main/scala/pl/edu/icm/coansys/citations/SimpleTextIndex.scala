package pl.edu.icm.coansys.citations

import org.apache.hadoop.io.Text

/**
 * A class to make using SimpleIndex[Text, V] easier.
 *
 * @author Mateusz Fedoryszak (m.fedoryszak@icm.edu.pl)
 */
class SimpleTextIndex[V <: org.apache.hadoop.io.Writable : Manifest](override val indexFileUri: String)
  extends SimpleIndex[Text, V](indexFileUri) {
  val text = new Text()

  def get(s: String) = {
    text.set(s)
    super.get(text)
  }

}
