# 更新日志

### 新增

- 支持渐变描边：新增 `bl_stroke_gradient_startColor`、`bl_stroke_gradient_endColor`（可选 `bl_stroke_gradient_centerColor`、`bl_stroke_gradient_angle`），配合 `bl_stroke_width` 使用即可让描边呈现渐变效果。
- 新增 RTL（从右到左）布局支持：`bl_corners_*`、`bl_padding_*`、`bl_stroke_position`、`bl_position` 中的 left/right 属性可在 RTL 布局下自动镜像。通过 `BackgroundLibrary.setRtlSupportEnabled(true)` 开启（默认关闭，不影响历史用户）。

### 修复


- 修复高版本 AndroidX（androidx core 1.1.0 及以上）下，背景 / 文字样式可能不生效的问题。
- 修复在 RadioButton / CheckBox 上同时设置 `bl_checked` 与 `bl_enabled` / `bl_unEnabled` 时，启用态 / 禁用态样式失效的问题。修复后禁用态会正确显示禁用样式，不再被选中态覆盖。
- 修复使用白色（`#FFFFFF`）时被误判为无效颜色的问题（影响 `bl_multi_text_selector`、`bl_multi_selector`、以及文字渐变 `bl_text_gradient_startColor` / `bl_text_gradient_endColor`）。
- 修复文字渐变水平方向（`bl_text_gradient_orientation="horizontal"`）不生效的问题。

### 改进

- `bl_*_stroke_color` 与 `bl_*_gradient` 系列支持只设置单个状态的颜色（此前需选中、未选中成对设置才会生效）。