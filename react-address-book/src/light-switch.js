import React from "react";
import { ReactComponent as OnIcon } from "./icons/bulb-on.svg";
import { ReactComponent as OffIcon } from "./icons/bulb-off.svg";

const ToggleButton = (props) => {
  const { isOn, onToggle } = props;

  const handleToggle = () => {
    onToggle(!isOn);
  };

  return (
    <span title="Toggle Light" className="light-icon" onClick={handleToggle}>
      {isOn ?  <OffIcon /> : <OnIcon />}
    </span>
  );
}

export default ToggleButton;
