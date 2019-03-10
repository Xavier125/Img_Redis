package vip.wefun.service;

import vip.wefun.pojo.Image;

import java.util.List;

public interface ImageService {
    List<Image> show();

    int deleteImageById(int id);
}
