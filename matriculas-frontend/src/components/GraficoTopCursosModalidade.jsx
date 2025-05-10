import { useEffect, useState } from 'react';
import { getTopCursos2022Modalidade } from '../services/api';
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid
} from 'recharts';

export default function GraficoTopCursosModalidade() {
  const [dados, setDados] = useState([]);
  const [modalidade, setModalidade] = useState('EaD');

  useEffect(() => {
    buscar();
  }, [modalidade]);

  const buscar = async () => {
    if (!modalidade) return;
    const res = await getTopCursos2022Modalidade(modalidade);
    setDados(res.data);
  };
  
  return (
    <div>
      <div className="filtros">
        <select value={modalidade} onChange={(e) => setModalidade(e.target.value)}>
          <option value="EaD">EaD</option>
          <option value="Presencial">Presencial</option>
        </select>
      </div>

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
    </div>
  );
}
