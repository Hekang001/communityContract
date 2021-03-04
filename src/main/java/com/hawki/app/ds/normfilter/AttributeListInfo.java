package com.hawki.app.ds.normfilter;

/**
 * @Author 何慷
 * @create 2021/3/4 上午9:50
 */

import java.util.*;

/**
 * 抽象属性列表
 *
 * @param <Tvalue> 属性值
 * @param <Tgroup> 属性类别
 */
public class AttributeListInfo<Tvalue extends Comparable<Tvalue>, Tgroup extends Comparable<Tgroup>> {

    /**
     * 属性条目列表
     */
    protected List<AttributeInfo<Tvalue, Tgroup>> attrList;

    protected boolean isFinishAdd = false;

    protected List<Tvalue> valueList;

    protected List<Tgroup> groupList;

    public AttributeListInfo(){
        attrList = new ArrayList<AttributeInfo<Tvalue,Tgroup>>();
        valueList = new ArrayList<Tvalue>();
        groupList = new ArrayList<Tgroup>();
    }

    /**
     * 属性值集合大小
     * @return
     */
    public int valueLength(){
        if(!isFinishAdd){
            throw new UnsupportedOperationException();
        }
        return this.valueList.size();
    }

    /**
     * 类别集合大小
     * @return
     */
    public int groupLength(){
        if(!isFinishAdd){
            throw new UnsupportedOperationException();
        }
        return this.groupList.size();

    }

    /**
     * 获取属性列表
     * @return
     */
    public List<AttributeInfo<Tvalue, Tgroup>> getAttrList(){
        return this.attrList;
    }

    /**
     * 获取属性值条目大小
     * @return
     */
    public int attrListLength(){
        return this.attrList.size();
    }

    /**
     * 向属性列表中添加属性
     * @param e
     */
    public void add(AttributeInfo<Tvalue, Tgroup> e){
        if(isFinishAdd){
            throw new UnsupportedOperationException();
        }
        this.attrList.add(e);
    }

    public boolean finishAdd(){
        if(!isFinishAdd){
            computeAttributeInfos();
        }
        return isFinishAdd;
    }

    protected void computeAttributeInfos(){
        Set<Tvalue> setValue = new HashSet<Tvalue>();
        Set<Tgroup> setGroup = new HashSet<Tgroup>();
        for(AttributeInfo<Tvalue, Tgroup> a:attrList){
            setValue.add(a.getValue());
            setGroup.add(a.getGroup());
        }
        Iterator<Tvalue> itValue = setValue.iterator();
        while(itValue.hasNext()){
            valueList.add(itValue.next());
        }
        Iterator<Tgroup> itGroup= setGroup.iterator();
        while(itGroup.hasNext()){
            groupList.add(itGroup.next());
        }
        for(AttributeInfo<Tvalue, Tgroup> a:attrList){
            a.setSerialValue(valueList.indexOf(a.getValue()));
            a.setSerialGroup(groupList.indexOf(a.getGroup()));
        }

        isFinishAdd = true;
    }

}
