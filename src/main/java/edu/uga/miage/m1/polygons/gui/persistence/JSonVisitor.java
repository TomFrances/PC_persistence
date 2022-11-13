package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.utils.ShapeUtils;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.List;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;

    @Override
    public void visit(Shape shape) {
        this.representation  = createRepresentation(shape);
    }

    private String createRepresentation(Shape shape) {
        if(shape instanceof GroupShape groupeShape){
            List<Shape> shapeList = groupeShape.getShapes();

            JsonArrayBuilder array = Json.createArrayBuilder();
            shapeList.forEach(s ->
                    array.add(Json.createObjectBuilder()
                    .add("type", ShapeUtils.getType(s))
                    .add("x",s.getX())
                    .add("y",s.getY())));
            return Json.createObjectBuilder()
                    .add("type", ShapeUtils.getType(shape))
                    .add("x",shape.getX())
                    .add("y",shape.getY())
                    .add("color",((GroupShape) shape).getColor().getRGB())
                    .add("shapes", array)
                    .build()
                    .toString();
        }
        return Json.createObjectBuilder()
                .add("type", ShapeUtils.getType(shape))
                .add("x",shape.getX())
                .add("y",shape.getY())
                .build()
                .toString();
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
