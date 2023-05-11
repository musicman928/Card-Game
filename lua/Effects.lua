local Effects = {}
complex = Effects
setmetatable(Effects, { __index = _G})

local function draw(number)
    print("draw " .. tostring(number))
end

Effects.draw = draw

local function getController()

end

return Effects


