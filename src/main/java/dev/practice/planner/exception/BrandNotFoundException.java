package dev.practice.planner.exception;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(Long brandId) {
        super("Brand with id " + brandId + " was not found");
    }
}
