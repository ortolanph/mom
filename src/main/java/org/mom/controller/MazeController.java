package org.mom.controller;

import org.mom.maze.Room;
import org.mom.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/maze/*",
        produces = {"application/json;charset=UTF-8"})
public class MazeController {

    @Autowired
    private MazeService service;

    @GetMapping("/firstRoom")
    public
    @ResponseBody
    Room firtRoom() {
        return service.firstRoom();
    }

    @GetMapping("/roomAt/{x}/{y}")
    public
    @ResponseBody
    Room roomAt(@PathVariable int x, @PathVariable int y) {
        return service.roomAt(x,y);
    }

    @GetMapping("/endMaze")
    public
    @ResponseBody
    Room endMaze() {
        service.endMaze();
        return service.firstRoom();
    }

}
