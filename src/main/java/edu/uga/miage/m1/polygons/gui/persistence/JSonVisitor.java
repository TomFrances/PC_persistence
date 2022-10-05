package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.utils.SimpleShapeType;

import javax.json.Json;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;

    @Override
    public void visit(SimpleShape shape) {
        this.representation  = createRepresentation(shape);
    }

    private String createRepresentation(SimpleShape shape) {
        return Json.createObjectBuilder()
                .add("type", SimpleShapeType.get(shape))
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
