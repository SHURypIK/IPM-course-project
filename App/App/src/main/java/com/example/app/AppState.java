package com.example.app;

import com.example.app.model.*;

import java.util.ArrayList;

public class AppState {
    private static final AppState instance = new AppState();
    private AppState() {}
    public static AppState getInstance() {
        return instance;
    }

    private User user;
    private String accessKey;
    private Page<ResidentShort> residentPage;
    private ResidentShort residentShort;
    private Resident resident;
    private ArrayList<Dormitory> dormitories;
    private ArrayList<Floor> floors;
    private ArrayList<Block> blocks;
    private ArrayList<Room> rooms;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getAccessKey() {
        return accessKey;
    }
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    public Page<ResidentShort> getResidentPage() {
        return residentPage;
    }
    public void setResidentPage(Page<ResidentShort> residentPage) {
        this.residentPage = residentPage;
    }
    public ResidentShort getResidentShort() {
        return residentShort;
    }
    public void setResidentShort(ResidentShort residentShort) {
        this.residentShort = residentShort;
    }
    public Resident getResident() {
        return resident;
    }
    public void setResident(Resident resident) {
        this.resident = resident;
    }
    public ArrayList<Dormitory> getDormitories() {
        return dormitories;
    }
    public void setDormitories(ArrayList<Dormitory> dormitories) {
        this.dormitories = dormitories;
    }
    public ArrayList<Floor> getFloors() {
        return floors;
    }
    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Dormitory findByNumber(int number){
        for(Dormitory dormitory: dormitories)
            if(dormitory.getNumber() == number)
                return dormitory;
        return null;
    }

    public Floor findByNumber(Dormitory dormitory, int number){
        for(Floor floor: dormitory.getFloors())
            if(floor.getNumber() == number)
                return floor;
        return null;
    }

    public Block findByNumber(Floor floor, int number){
        for(Block block: floor.getBlocks())
            if(block.getNumber() == number)
                return block;
        return null;
    }

    public Room findByNumber(Block block, int number){
        for(Room room: block.getRooms())
            if(room.getNumber() == number)
                return room;
        return null;
    }

    public Room findByResident(){
        for(Room room: rooms)
            for (Resident resident: room.getResidents())
                if(resident.getFio().equals(this.resident.getFio()))
                    return room;
        return null;
    }

    public Block findByRoom(int id){
        for(Block block: blocks)
            for (Room room: block.getRooms())
                if(room.getId() == id)
                    return block;
        return null;
    }

    public Floor findByBlock(int id){
        for(Floor floor: floors)
            for (Block block: floor.getBlocks())
                if(block.getId() == id)
                    return floor;
        return null;
    }

    public Dormitory findByFloor(int id){
        for(Dormitory dormitory: dormitories)
            for (Floor floor: dormitory.getFloors())
                if(floor.getId() == id)
                    return dormitory;
        return null;
    }
}
