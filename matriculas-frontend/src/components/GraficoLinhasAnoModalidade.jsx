import { useEffect, useState } from 'react';
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from 'recharts';
import { getPorAnoModalidade } from '../services/api';

export default function GraficoLinhasAnoModalidade() {
  const [modalidade, setModalidade] = useState('EaD');
  const [dados, setDados] = useState([]);

  useEffect(() => {
    const buscarDados = async () => {
      try {
        const res = await getPorAnoModalidade(modalidade);
        setDados(res.data);
      } catch (err) {
        console.error('Erro ao buscar dados por modalidade:', err);
      }
    };

    buscarDados();
  }, [modalidade]);

  return (
    <div>
      <div style={{ marginBottom: '1rem' }}>
        <label>Modalidade: </label>
        <select
          value={modalidade}
          onChange={(e) => setModalidade(e.target.value)}
        >
          <option value="EaD">EaD</option>
          <option value="Presencial">Presencial</option>
        </select>
      </div>

      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={dados}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="chave" />
          <YAxis />
          <Tooltip />
          <Line
            type="monotone"
            dataKey="total"
            stroke="#EC5E8F"
            strokeWidth={3}
            dot={{ r: 4 }}
            activeDot={{ r: 6 }}
            label={{ position: 'top' }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}
