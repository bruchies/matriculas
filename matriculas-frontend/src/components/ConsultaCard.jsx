import React from 'react';

const ConsultaCard = ({ titulo, valor }) => {
  return (
    <div className="card">
      <h3>{titulo}</h3>
      <p>{valor.toLocaleString()}</p>
    </div>
  );
};

export default ConsultaCard;