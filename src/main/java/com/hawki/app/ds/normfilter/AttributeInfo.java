package com.hawki.app.ds.normfilter;

/**
 * @Author 何慷
 * @create 2021/3/4 上午9:49
 */

/**
 * 属性值及类别组合类
 *
 */
class AttributeInfo<Tvalue extends Comparable<Tvalue>, Tgroup extends Comparable<Tgroup>>{

    /**
     * 属性值
     */
    protected Tvalue value;

    /**
     *
     * 所属类别
     */
    protected Tgroup group;

    protected int serialValue;

    protected int serialGroup;


    public AttributeInfo(){

    }

    public AttributeInfo(Tvalue value, Tgroup group){
        this.value = value;
        this.group = group;
    }


    /**
     * 属性值属于的序列
     * @return
     */
    public int belongToValue(){
        return this.serialValue;
    }

    public Tvalue getValue() {
        return value;
    }

    public void setValue(Tvalue value) {
        this.value = value;
    }

    public Tgroup getGroup() {
        return group;
    }

    public void setGroup(Tgroup group) {
        this.group = group;
    }

    /**
     * 属性类别属于的序列
     * @return
     */
    public int belongToGroup(){
        return this.serialGroup;
    }

    public int getSerialValue() {
        return serialValue;
    }

    public void setSerialValue(int serialValue) {
        this.serialValue = serialValue;
    }

    public int getSerialGroup() {
        return serialGroup;
    }

    public void setSerialGroup(int serialGroup) {
        this.serialGroup = serialGroup;
    }

}
