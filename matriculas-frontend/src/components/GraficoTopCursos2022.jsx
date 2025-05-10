import React, { useEffect, useState } from 'react';
import { getTopCursos2022 } from '../services/api';
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid } from 'recharts';

export default function GraficoTopCursos2022() {
  const [dados, setDados] = useState([]);

  useEffect(() => {
    getTopCursos2022().then(res => setDados(res.data));
  }, []);

  return (
    <ResponsiveContainer width="100%" height={300}>
      <BarChart data={dados}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis 
        className="texto-grafico"
        dataKey="chave" 
        angle={-20} 
        textAnchor="end" 
        interval={0} 
        height={100}/>
        <YAxis />
        <Tooltip />
        <Bar dataKey="total" fill="#EC5E8F" />
      </BarChart>
    </ResponsiveContainer>
  );
}
