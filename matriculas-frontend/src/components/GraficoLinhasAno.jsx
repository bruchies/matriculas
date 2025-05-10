import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from 'recharts';

export default function GraficoLinhasAno({ dados }) {
  return (
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
        />
      </LineChart>
    </ResponsiveContainer>
  );
}
