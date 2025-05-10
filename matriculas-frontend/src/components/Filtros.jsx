import React, { useState } from 'react';

export default function Filtros({ aplicarFiltros }) {
  const [modalidade, setModalidade] = useState('');
  const [estado, setEstado] = useState('');

  const buscar = () => {
    aplicarFiltros(estado.trim().toUpperCase(), modalidade);
  };

  return (
    <div style={{ marginBottom: '20px' }}>
      <select onChange={(e) => setModalidade(e.target.value)}>
        <option value="">Modalidade</option>
        <option value="EaD">EaD</option>
        <option value="Presencial">Presencial</option>
      </select>
      <input
        type="text"
        placeholder="Estado (ex: RS)"
        onChange={(e) => setEstado(e.target.value)}
        style={{ marginLeft: '10px' }}
      />
      <button onClick={buscar} style={{ marginLeft: '10px' }}>
        Aplicar Filtros
      </button>
    </div>
  );
}
