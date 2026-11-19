# Software Engineering Diagrams – Scenario 12

This folder contains four corrected diagrams for the **Online Food Ordering and Restaurant Sales Analysis System**. The visual organization follows the four reference styles supplied in the user's PDF:

1. **class_diagram** – UML class boxes with stereotypes, attributes, operations, associations, multiplicities, a composition relationship, and a JDBC dependency.
2. **er_diagram** – Chen-style ER model using entity rectangles, relationship diamonds, attribute ovals, and 1:N cardinalities. Entities and attributes match the MySQL schema.
3. **architecture** – layered architecture with user, application, business/data-access, MySQL resource, and Python analytics layers, arranged as separate labeled bands.
4. **use_case** – UML use-case system boundary with staff/manager actors, use-case ovals, and included order validation and total calculation.

Each diagram is provided as an editable Graphviz `.dot` source, a scalable `.svg`, and a `.png` preview. The diagrams describe the existing console + MySQL + Python project; they do not introduce a website, REST API, or cloud deployment layer.

To regenerate SVGs and PNGs, install Graphviz and run:

```bash
for f in class_diagram er_diagram architecture use_case; do
  dot -Tsvg "$f.dot" -o "$f.svg"
  dot -Tpng -Gdpi=140 "$f.dot" -o "$f.png"
done
```
