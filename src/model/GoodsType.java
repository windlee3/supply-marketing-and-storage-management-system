package model;
public class GoodsType {
    private Integer typeId;
    private String typeName;
    private Integer parentId;

    public GoodsType() {
    }

    public GoodsType(int typeId, String typeName, Integer parentId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.parentId = parentId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getParentId() {
        return parentId;
    }
}