import { useEffect, useState } from 'react';
import { getTopCursosEstadoModalidade } from '../services/api';
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid
} from 'recharts';

const estados = [
  "SÃO PAULO", "RIO DE JANEIRO", "SANTA CATARINA", "MINAS GERAIS", "GOIÁS",
  "CEARÁ", "DISTRITO FEDERAL", "RIO GRANDE DO NORTE", "PERNAMBUCO", "SERGIPE",
  "PARANÁ", "RIO GRANDE DO SUL", "MARANHÃO", "PARÁ", "BAHIA", "AMAZONAS",
  "PIAUÍ", "MATO GROSSO", "PARAÍBA", "ALAGOAS", "RORAIMA", "AMAPÁ", "RONDÔNIA",
  "MATO GROSSO DO SUL", "ESPÍRITO SANTO", "ACRE", "TOCANTINS"
];

export default function GraficoTopCursosEstadoModalidade() {
  const [estado, setEstado] = useState('RIO GRANDE DO SUL');
  const [modalidade, setModalidade] = useState('EaD');
  const [dados, setDados] = useState([]);

  useEffect(() => {
    buscar();
  }, [estado, modalidade]);

  const buscar = async () => {
    if (!estado || !modalidade) return;
    const res = await getTopCursosEstadoModalidade(estado, modalidade);
    setDados(res.data);
  };

  return (
    <div>
      <div className="filtros">
        <select value={estado} onChange={(e) => setEstado(e.target.value)}>
          {estados.map((uf) => (
            <option key={uf} value={uf}>{uf}</option>
          ))}
        </select>

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
