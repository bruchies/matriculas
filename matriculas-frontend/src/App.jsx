import { Routes, Route } from 'react-router-dom';
import Menu from './pages/Menu';
import Dashboard from './pages/Dashboard';
import GraficoTotalAno from './pages/GraficoTotalAno';
import GraficoTotalAnoModalidade from './pages/GraficoTotalAnoModalidade';
import RankingModalidade from './pages/RankingModalidade';
import RankingEstadoModalidade from './pages/RankingEstadoModalidade';
import TopCursos2022 from './pages/TopCursos2022';

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Menu />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/grafico-total-ano" element={<GraficoTotalAno />} />
      <Route path="/grafico-total-ano-modalidade" element={<GraficoTotalAnoModalidade />} />
      <Route path="/ranking-modalidade" element={<RankingModalidade />} />
      <Route path="/ranking-estado-modalidade" element={<RankingEstadoModalidade />} />
      <Route path="/ranking-top-cursos-2022" element={<TopCursos2022 />} />
    </Routes>
  );
}
