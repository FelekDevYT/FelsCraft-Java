local var = 1

function setup()
    if fc and fc.scheduler and fc.scheduler.run then
        print("Scheduler API is available")
    else
        print("Scheduler API is MISSING!")
    end

    fc.debugging.log("info", "This is so cool!")

    fc.block.register("snow", {r = 255, g = 255, b = 255})
    fc.inventory.addItem("snow")

    fc.event.subscribe("player.moved", playerMoved)
    
    fc.scheduler.run("name", scheduler, 1000)

    print(fc.player.getCurrentLevel())
end

function scheduler()
    if (var == 4) then
        fc.scheduler.restart("name")
        var = 0
    end

    var = var + 1
    print(var)
end

function update()
end

function playerMoved(oldX, oldY, dx, dy)
    print("You're moved!")
end