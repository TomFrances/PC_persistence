package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.utils.ShapeUtils;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    @Override
    public void visit(Shape shape) {
        this.representation = createRepresentation(shape);
    }

    private String createRepresentation(Shape shape) {
        if(shape instanceof GroupShape groupShape){
            String shapeList = "";
            for (Shape s : groupShape.getShapes()) {
                shapeList = shapeList.concat(String.format("<groupShape><type>%s</type><x>%d</x><y>%d</y></groupShape>", ShapeUtils.getType(s), s.getX(), s.getY()));
            }
            return String.format("<shape><type>%s</type><x>%d</x><y>%d</y><color>%s</color><shapes>%s</shapes></shape>", ShapeUtils.getType(shape), shape.getX(), shape.getY(), ((GroupShape) shape).getColor().getRGB(),shapeList);
        }
        return String.format("<shape><type>%s</type><x>%d</x><y>%d</y></shape>", ShapeUtils.getType(shape), shape.getX(), shape.getY());
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     * <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
