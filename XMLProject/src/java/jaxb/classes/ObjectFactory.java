
package jaxb.classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.classes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Blocks_QNAME = new QName("http://xml.netbeans.org/schema/blocks", "blocks");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.classes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListBlock }
     * 
     */
    public ListBlock createListBlock() {
        return new ListBlock();
    }

    /**
     * Create an instance of {@link BlockItem }
     * 
     */
    public BlockItem createBlockItem() {
        return new BlockItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBlock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/blocks", name = "blocks")
    public JAXBElement<ListBlock> createBlocks(ListBlock value) {
        return new JAXBElement<ListBlock>(_Blocks_QNAME, ListBlock.class, null, value);
    }

}
